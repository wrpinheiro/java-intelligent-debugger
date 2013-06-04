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

import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;

/**
 * @author wrp
 *
 * 31/01/2009
 */
public class SomaNNumerosModel extends IntegerSystem {
/*
	Soma n inteiros.

	01 #include <stdio.h>
	02 int main () {
	03   int n, i, sum,number;
	04   printf ("input n\n");
	05   scanf("%d", &n);
	06   sum = 0;
	07   i=1;
	08   while(i <= n) {
	09     printf ("input number:\n");
	10     scanf("%d", &number);
	11     sum = sum + 1;
	12     i = i+ 1;
	13   }
	14   printf ("Sum %d\n", sum);
	15   return 0;
	16 }
*/
	
	public SomaNNumerosModel() {
		super("SomaNNumerosModel", null);
	}

}
