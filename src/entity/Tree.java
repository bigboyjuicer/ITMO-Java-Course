package entity;

import types.Colors;
import types.Properties;
import types.Sizes;

import java.util.List;

public class Tree {

    private String name;
    private List<Leaf> leaves;

    public class Leaf {
        private Sizes size;
        private Colors color;
        private Properties property;

        public Leaf(Sizes size, Colors color, Properties property) {
            this.size = size;
            this.color = color;
            this.property = property;
        }
    }

    public Tree(String name) {
        this.name = name;
    }

    public void hasLeaves() {
        System.out.println(name + " has " + leaves.get(0).size.getName() + " " + leaves.get(0).color.getName() + " " + leaves.get(0).property.getName() + " leaves");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Leaf> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leaf> leaves) {
        this.leaves = leaves;
    }
}
