export default {
  C:`
  #include <stdio.h>
 
  int main()
  {
      /* 我的第一个 C 程序 */
      printf("Hello, World!");
   
      return 0;
  }`,
  CPP: `
  #include <iostream>
  using namespace std;
  int main()
  {
      cout << "Hello, world!" << endl;
      return 0;
  }
    `,
  PYTHON3: `print("Hello, World!")`,
  JAVA: `
    class Untitled {
        public static void main(String[] args) {
            System.out.println("Hello, World!");
        }
    }
    `,
  GOLANG: `
  package main

  import "fmt"
  
  func main() {
      fmt.Println("Hello, World!")
  }
  
  `,
};

export const TypeMap = {
  CPP: "text/x-c++src",
  JAVA: "text/x-java",
  GOLANG: "text/x-go",
  PYTHON3: "text/x-python",
};
