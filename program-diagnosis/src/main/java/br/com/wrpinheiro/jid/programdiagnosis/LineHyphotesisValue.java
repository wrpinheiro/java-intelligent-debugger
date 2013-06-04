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
package br.com.wrpinheiro.jid.programdiagnosis;

import java.util.Set;

import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.util.DefaultSets;

/**
 * A simple wrapper for a hyphotesis value that maps only lines.
 * @author wrp
 */
public class LineHyphotesisValue extends HypothesisValue {
	/**
	 * The lines.
	 */
	private Set<Integer> lines;

	/**
	 * Create an instance mapping lines.
	 * @param lines the lines.
	 */
	public LineHyphotesisValue(final Set<Integer> lines) {
		super((IntegerComponent)null);
		this.lines = lines;
	}

	/**
	 * A wrapper for mapping only one line.
	 * @param line a line.
	 */
	public LineHyphotesisValue(final int line) {
		this(DefaultSets.toSet(line));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see br.com.jdebugger.programdiagnosis.HypothesisValue#getProgramLines()
	 */
	@Override
	public Set<Integer> getProgramLines() {
		return lines;
	}
}
