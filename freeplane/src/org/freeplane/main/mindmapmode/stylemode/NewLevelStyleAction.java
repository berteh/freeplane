/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2009 Dimitry
 *
 *  This file author is Dimitry
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.main.mindmapmode.stylemode;

import java.awt.event.ActionEvent;

import org.freeplane.core.controller.Controller;
import org.freeplane.core.resources.NamedObject;
import org.freeplane.core.ui.AFreeplaneAction;
import org.freeplane.core.undo.IActor;
import org.freeplane.features.common.map.MapModel;
import org.freeplane.features.common.map.NodeModel;
import org.freeplane.features.common.styles.MapStyleModel;
import org.freeplane.features.mindmapmode.map.MMapController;

/**
 * @author Dimitry Polivaev
 * 02.10.2009
 */
public class NewLevelStyleAction extends AFreeplaneAction {
	public NewLevelStyleAction() {
		super("NewLevelStyleAction");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void actionPerformed(final ActionEvent e) {
		final MapModel map = Controller.getCurrentController().getMap();
		final NodeModel levelStyleParentNode = getLevelStyleParentNode(map);
		final String styleName = "AutomaticLayout.level," + levelStyleParentNode.getChildCount();
		final NamedObject styleObject = NamedObject.format(styleName);
		final MapStyleModel styleModel = MapStyleModel.getExtension(map);
		final MMapController mapController = (MMapController) Controller.getCurrentModeController().getMapController();
		final NodeModel node = new NodeModel(map);
		node.setUserObject(styleObject);
		mapController.insertNode(node, levelStyleParentNode, false, false, true);
		mapController.select(node);
		final IActor actor = new IActor() {
			public void undo() {
				styleModel.removeStyleNode(node);
			}

			public String getDescription() {
				return "NewLevelStyle";
			}

			public void act() {
				styleModel.addStyleNode(node);
			}
		};
		Controller.getCurrentModeController().execute(actor, map);
	}

	private NodeModel getLevelStyleParentNode(final MapModel map) {
		return (NodeModel) map.getRootNode().getChildAt(1);
	}
}
