package com.amrdeveloper.treeviewlib.filestree;

import com.amrdeveloper.treeviewlib.R;

public class ExtensionTable {

    public static int getExtensionIcon(String extension) {
        return switch (extension) {
            case ".c" -> R.drawable.ic_c;
            case ".cpp" -> R.drawable.ic_cpp;
            case ".cs" -> R.drawable.ic_cs;
            case ".git" -> R.drawable.ic_git;
            case ".go" -> R.drawable.ic_go;
            case ".gradle" -> R.drawable.ic_gradle;
            case ".java" -> R.drawable.ic_java;
            default -> R.drawable.ic_file;
        };
    }
}
