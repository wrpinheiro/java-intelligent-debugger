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

/**
 * A class for representing additional information for the components.
 * 
 * @author wrp
 */
public class ComplementaryInfo {
  public static final ComplementaryInfo EMPTY_INFO = new ComplementaryInfo("");

  private String nodeStr;

  public ComplementaryInfo(String nodeStr) {
    this.nodeStr = nodeStr;
  }

  public String getNodeStr() {
    return this.nodeStr;
  }

  /**
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    if (this.nodeStr == null)
      return "";
    else
      return this.nodeStr;
  }

}
