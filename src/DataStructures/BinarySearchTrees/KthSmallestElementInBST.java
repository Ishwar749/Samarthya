package DataStructures.BinarySearchTrees;

// Problem: https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

public class KthSmallestElementInBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public int kthSmallest(TreeNode root, int k) {
            int[] nodeNum = new int[1];
            int[] ans = new int[1];

            findAns(root, nodeNum, ans, k);

            return ans[0];
        }

        static void findAns(TreeNode root, int[] nodeNum, int[] ans, int k) {
            if (root.left != null) {
                findAns(root.left, nodeNum, ans, k);
            }

            nodeNum[0]++;
            if (nodeNum[0] == k) {
                ans[0] = root.val;
                return;
            }

            if (root.right != null) {
                findAns(root.right, nodeNum, ans, k);
            }
        }
    }
}
