/**
 * author: Marcel Genzmehr
 * 26.01.2012
 */
package org.freeplane.plugin.workspace.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.freeplane.plugin.workspace.model.node.AWorkspaceTreeNode;

/**
 * 
 */
public class IOController {
	
	private final HashMap<Class<? extends AWorkspaceTreeNode>, HashMap<Integer, List<IWorkspaceNodeEventListener>>> listenerMap = new HashMap<Class<? extends AWorkspaceTreeNode>, HashMap<Integer, List<IWorkspaceNodeEventListener>>>();
	/***********************************************************************************
	 * CONSTRUCTORS
	 **********************************************************************************/

	/***********************************************************************************
	 * METHODS
	 * @param eventType 
	 * @param node 
	 **********************************************************************************/
	public List<IWorkspaceNodeEventListener> getNodeEventListeners(Class<? extends AWorkspaceTreeNode> clazz, Integer eventType) {
		HashMap<Integer, List<IWorkspaceNodeEventListener>> levelOne = listenerMap.get(clazz);
		if(levelOne != null) {		
			return levelOne.get(eventType);	
		}
		return null;
	}
	
	
	public void registerNodeEventListener(Class<? extends AWorkspaceTreeNode> clazz, Integer eventType, IWorkspaceNodeEventListener listener) {
		HashMap<Integer, List<IWorkspaceNodeEventListener>> levelOne = listenerMap.get(clazz);
		if(levelOne == null) {		
			HashMap<Integer, List<IWorkspaceNodeEventListener>> levelTwo = new HashMap<Integer, List<IWorkspaceNodeEventListener>>();
			Vector<IWorkspaceNodeEventListener> vec = new Vector<IWorkspaceNodeEventListener>();
			vec.add(listener);
			levelTwo.put(eventType, vec);
			listenerMap.put(clazz, levelTwo);		
		}
		else {
			List<IWorkspaceNodeEventListener> listeners = levelOne.get(eventType);
			if(!listeners.contains(listener)) {
				listeners.add(listener);
			}
		}
		
	}
	
	/***********************************************************************************
	 * REQUIRED METHODS FOR INTERFACES
	 **********************************************************************************/
}