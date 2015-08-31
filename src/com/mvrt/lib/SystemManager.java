package com.mvrt.lib;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class SystemManager {
	private static SystemManager inst = null;
	
	private class StateMachineHolder {
		StateMachine machine;
		StateHolder stateHolder = new StateHolder();
		
		public StateMachineHolder(StateMachine sm) {
			machine = sm;
		}
		
		public StateHolder getStateHolder() {
			return stateHolder;
		}
		
		public void update() {
			machine.getState(stateHolder);
		}
	}
	
	private HashMap<String, StateMachineHolder> stateMachines;
	
	public static SystemManager getInstance() {
		if (inst == null) {
			inst = new SystemManager();
		}
		return inst;
	}
	
	public SystemManager() {
		this.stateMachines = new HashMap<String, StateMachineHolder>();
	}
	
	public void add(StateMachine sm) {
		StateMachineHolder smh = new StateMachineHolder(sm);
		stateMachines.put(sm.getName(), smh);
	}
	
	public void add(Collection<StateMachine> values) {
		for (StateMachine sm : values) {
			StateMachineHolder smh = new StateMachineHolder(sm);
			stateMachines.put(sm.getName(), smh);
		}
	}
	
	private void updateStates(String system_key) {
		stateMachines.get(system_key).update();
	}
	
	private void updateAllStates() {
		Set<String> keys = stateMachines.keySet();
		for (String key : keys) {
			updateStates(key);
		}
	}
}
