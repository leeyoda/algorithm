/*
 * Copyright 2019 rugalbernstein.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ga.rugal.amazon.binarytreeinordertraversal;

import java.util.ArrayList;
import java.util.List;

import ga.rugal.amazon.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 * @author rugalbernstein
 */
public class Solution {

  private final List<Integer> result = new ArrayList<>();

  public List<Integer> inorderTraversal(final TreeNode root) {
    this.helper(root);
    return this.result;
  }

  public void helper(final TreeNode root) {
    if (root != null) {
      this.helper(root.left);
      this.result.add(root.val);
      this.helper(root.right);
    }
  }
}
