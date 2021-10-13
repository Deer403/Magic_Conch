package com.two.conchserver.utils;

public enum LanguageDetails {

    C{
        @Override
        public String getFileName() {
            return "Main.c";
        }

        @Override
        public String getImageName() {
            return "gcc-run:latest";
        }
    },
    PYTHON3{
        @Override
        public String getFileName() {
            return "Main.py";
        }

        @Override
        public String getImageName() {
            return "python-run:latest";
        }
    },
    CPP{
        @Override
        public String getFileName() {
            return "Main.c";
        }

        @Override
        public String getImageName() {
            return "gcc-run:latest";
        }
    },
    Java{
        @Override
        public String getFileName() {
            return "Main.java";
        }

        @Override
        public String getImageName() {
            return "openjdk:latest";
        }
    },
    Go{
        @Override
        public String getFileName() {
            return "Main.go";
        }

        @Override
        public String getImageName() {
            return "golang:latest";
        }

    };


    //语言对应文件名称
    public abstract String getFileName();
    //语言对应Docker镜像名称
    public abstract String getImageName();
    //容器命名
    public String getContainerName() {
        return this.name() + "_" + Utils.getRandomCode(4);
    }
}
