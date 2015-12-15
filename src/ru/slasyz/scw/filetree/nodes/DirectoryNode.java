package ru.slasyz.scw.filetree.nodes;

import ru.slasyz.scw.filetree.exceptions.NodeAlreadyExists;
import ru.slasyz.scw.filetree.exceptions.NotFoundException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryNode extends Node {
    private List<Node> children;

    public DirectoryNode(String name) {
        super(name);

        children = new ArrayList<>();
    }

    public boolean contains(String name) {
        try {
            getChild(name);
            return true;
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public List<String> getContent() {
        List<String> result = new ArrayList<>();

        for (Node child : children)
            result.add(child.getName());

        return result;
    }

    public Node getChild(String name) throws NotFoundException {
        for (Node child : children) {
            if (child.getName().equals(name))
                return child;
        }

        throw new NotFoundException();
    }

    private void create(Node node) throws NodeAlreadyExists {
        if (contains(node.getName()))
            throw new NodeAlreadyExists();

        children.add(node);
    }

    public void createFile(String name) throws NodeAlreadyExists {
        create(new FileNode(name));
    }

    public void createDirectory(String name) throws NodeAlreadyExists {
        create(new DirectoryNode(name));
    }
}