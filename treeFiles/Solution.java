import java.io.*;
import java.util.*;

/*
Проход по дереву файлов, отбираем файлы удовлетворяющие условию и записываем их содержимое в конечный файл.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        List<File> list = new ArrayList<>();
        Set<File> treeset = new TreeSet<>();
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        FileOutputStream outputStream = new FileOutputStream(allFilesContent, true);
        File dir = new File(args[0]);
        while (true) {
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) {
                    list.add(new File(item.getAbsolutePath()));
                } else {
                    if (item.getAbsoluteFile().length() <= 50 && item.getName().endsWith(".txt") && !item.equals(allFilesContent)){
                        treeset.add(item.getAbsoluteFile());
                    }
                }
            }
            if (list.isEmpty()){
                break;
            }
            dir = list.get(0);
            list.remove(0);
        }
        Iterator<File> iterator = treeset.iterator();
        while (iterator.hasNext()){
                String fileout = iterator.next()+ "";
                        File filein = new File(fileout);
                        FileInputStream inputStream = new FileInputStream(filein);
                        while (inputStream.available() > 0){
                            int bytte = inputStream.read();
                            outputStream.write(bytte);
                        }
                        outputStream.write(10);
                        outputStream.flush();
                        inputStream.close();
        }
            outputStream.close();
    }
}
