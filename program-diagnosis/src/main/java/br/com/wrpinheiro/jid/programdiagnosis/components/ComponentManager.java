/*
 * Copyright 2006-2013 Wellington Ricardo Pinheiro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.wrpinheiro.jid.programdiagnosis.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ComponentManager {
	private static ComponentManager instance;

	private Stack<List<IntegerComponent>> marks = new Stack<List<IntegerComponent>>(); 

	private boolean marked;
	
	private ComponentManager() {
	}
	
	public static ComponentManager getInstance() {
		if (instance == null) {
			instance = new ComponentManager();
		}
		return instance;
	}
	
	public void mark() {
	  marks.push(new ArrayList<IntegerComponent>());
	  marked = true;
	}
	
	public List<IntegerComponent> release() {
	  List<IntegerComponent> result = marks.pop();
	  marked = marks.size() > 0;
	  return result;
	}
	
	public void register(IntegerComponent comp) {
	  if (marked)
	    marks.peek().add(comp);
	}
}
