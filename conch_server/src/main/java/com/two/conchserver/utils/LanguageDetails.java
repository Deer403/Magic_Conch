package com.two.conchserver.utils;

public enum LanguageDetails {

    C{
        @Override
        public String getFileName() {
            return "main.c";
        }

        @Override
        public String getImageName() {
            return "gcc-run:latest";
        }

        @Override
        public String getRunCommand() {
            return "gcc "+getFileName()+" -o main && ./main";
        }
    },
    PYTHON3{
        @Override
        public String getFileName() {
            return "main.py";
        }

        @Override
        public String getImageName() {
            return "python-run:latest";
        }

        @Override
        public String getRunCommand() {
            return "python "+getFileName();
        }
    },
    CPP{
        @Override
        public String getFileName() {
            return "main.cpp";
        }

        @Override
        public String getImageName() {
            return "gcc-run:latest";
        }

        @Override
        public String getRunCommand() {
            return "g++ "+getFileName()+" -o main && ./main";
        }
    },
    JAVA{
        @Override
        public String getFileName() {
            return "main.java";
        }

        @Override
        public String getImageName() {
            return "openjdk:latest";
        }

        @Override
        public String getRunCommand() {
            return "java "+getFileName();
        }
    },
    Go{
        @Override
        public String getFileName() {
            return "main.go";
        }

        @Override
        public String getImageName() {
            return "golang:latest";
        }

        @Override
        public String getRunCommand() {
            return "go run "+getFileName();
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
    //容器运行程序命令
    public abstract String getRunCommand();

}

