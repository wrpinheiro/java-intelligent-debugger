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
package br.com.wrpinheiro.jid.programdiagnostician.models.test;

public class Multa {
	public static int readInt() {
		return 0;
	}

	public static void writeInt(int i) {
		System.out.println(i);
	}

	public static void main(String[] args) {
		int valor, multa, mora, novoValor;
		valor = Integer.parseInt(args[0]);
		multa = Integer.parseInt(args[1]);
		mora = Integer.parseInt(args[2]);

		novoValor = valor;

		if (multa == 1)
			novoValor = novoValor + 50;
		else novoValor = novoValor -25;

		if (mora == 1)
			novoValor = novoValor - 5;
		else novoValor = novoValor + 15;

		System.out.println(novoValor);
	}
}
