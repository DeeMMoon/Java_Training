package day02.ex02;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class Commands {

    public static void ls(Path path) throws IOException {
        List<Path> files = Files.walk(path, 1).collect(Collectors.toList());
        for (int i = 0; i < files.size(); i++)
        {
            if (!files.get(i).equals(path))
                System.out.println(files.get(i).getFileName() + " " + Files.size(files.get(i)) + "KB");
        }
    }

    public static Path cd(Path path, String newFolder)
    {
        Path newPath = Paths.get(path + "/" + newFolder).normalize();
        if(Files.exists(newPath) && Files.isDirectory(newPath))
        {
            System.out.println(newPath);
            return newPath;
        }
        else
        {
            System.out.println("Invalid path");
            return path;
        }
    }
    public static void mv(Path path, String srcPath, String dstPath) throws IOException {
        Path src = Paths.get(path + "/" + srcPath).normalize();
        Path dst = Paths.get(path + "/" + dstPath).normalize();

        if(Files.isRegularFile(src))
        {
            if(Files.isDirectory(dst))
                dst = Paths.get(dst + "/" + src.getFileName()).normalize();
            Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
        }
        else
            System.out.println("Wrong filepath");
    }
}
