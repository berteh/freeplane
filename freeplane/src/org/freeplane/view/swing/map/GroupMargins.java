/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2013 Dimitry
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
package org.freeplane.view.swing.map;

/**
 * @author Dimitry Polivaev
 * 07.10.2013
 */
class GroupMargins{
	public int start;
	public int startY;
	public int endY;

	static GroupMargins[] create(int size){
		GroupMargins[] groups = new GroupMargins[size];
		for(int i = 0; i < size; i++)
			groups[i] = new GroupMargins();
		return groups;
	}

	public void beginFrom(int start) {
	    this.start = start;
	    startY = Integer.MAX_VALUE;
	    endY = Integer.MIN_VALUE;
    }

	public void extend(int startY, int endY){
		this.startY = Math.min(this.startY, startY);
		this.endY = Math.max(this.endY, endY);
	}

	public void set(int startY, int endY){
		this.startY = startY;
		this.endY = endY;
	}

	public void setMargins(boolean firstGroupNode, int startY, int endY){
		if(firstGroupNode)
	    	set(startY, endY);
	    else
	    	extend(startY, endY);
	}

}