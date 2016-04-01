package ga.rugal.tree;

public class RBTree<Key extends Comparable<Key>, Value> implements Tree<Key, Value>
{

    private TreeNode root = null;

    private int size = 0;

    private boolean isRed(TreeNode node)
    {
        return node == null ? false : node.red;
    }

    private TreeNode<Key, Value> insert(TreeNode<Key, Value> h, Key key, Value value)
    {
        if (null == h)
        {
            return new TreeNode<>(key, value);
        }
        int result = key.compareTo(h.getKey());
        if (0 == result)
        {
            h.setValue(value);
            return h;
        }
        if (result < 0)
        {
            h.left = this.insert(h.left, key, value);
        } else
        {
            h.right = this.insert(h.right, key, value);
        }
        if (isRed(h.right) && !isRed(h.left))
        {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left))
        {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right))
        {
            flipColors(h);
        }
        return h;
    }

    /**
     * Get the height of a node.<BR>
     * The height of a node is the number of edges on the longest path from the node to a leaf. <BR>
     * A leaf node will have a height of 0.<BR>
     * The depth of a node is the number of edges from the node to the tree's root node. <BR>
     * A root node will have a depth of 0.
     *
     * @param node return -1 height if given node is null.
     *
     * @return
     */
    private int height(TreeNode node)
    {
        return null != node ? node.height : -1;
    }

    private void flipColors(TreeNode h)
    {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.red = !h.red;
        h.left.red = !h.left.red;
        h.right.red = !h.right.red;
    }

    private TreeNode rotateRight(TreeNode parent)
    {
        TreeNode newParent = parent.left;
        parent.left = newParent.right;
        newParent.right = parent;
        newParent.red = newParent.right.red;
        newParent.right.red = true;
        parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
        newParent.height = Math.max(height(newParent.left), height(newParent.right)) + 1;
        return newParent;
    }

    // make a right-leaning link lean to the left
    private TreeNode rotateLeft(TreeNode parent)
    {
        TreeNode newParent = parent.right;
        parent.right = newParent.left;
        newParent.left = parent;
        newParent.red = newParent.left.red;
        newParent.left.red = true;
        parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
        newParent.height = Math.max(height(newParent.left), height(newParent.right)) + 1;
        return newParent;
    }

    @Override
    public void put(Key key, Value value)
    {
        this.root = this.insert(root, key, value);
        this.root.red = false;
    }

    @Override
    public Value get(Key key)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Key key)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Key key)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
