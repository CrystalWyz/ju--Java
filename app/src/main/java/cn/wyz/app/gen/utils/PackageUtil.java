package cn.wyz.app.gen.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

public class PackageUtil {

    /**
     * 获取抽象类的所有子类
     *
     * @param abstractClass 抽象类
     * @return 子类列表
     */
    public static List<Class<?>> getSubClasses(Class<?> abstractClass) {
        List<Class<?>> subClasses = new ArrayList<>();
        List<Class<?>> classes = PackageUtil.getClasses("cn.wyz");

        // 遍历每个类，找到继承自抽象类的子类
        for (Class<?> clazz : classes) {
            if (abstractClass.isAssignableFrom(clazz) && !abstractClass.equals(clazz)) {
                subClasses.add(clazz);
            }
        }

        return subClasses;
    }

    public static List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String packagePath = packageName.replace('.', '/');

        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            List<File> directories = new ArrayList<>();

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                directories.add(new File(resource.getFile()));
            }

            for (File directory : directories) {
                classes.addAll(findClasses(directory, packageName));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return classes;
    }

    private static Collection<? extends Class<?>> findClasses(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }

        return classes;
    }
}