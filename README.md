# AutoMail

- First of all, please add the `.jar` files which are in `lib` directory into your classpath. Assume your repository is stored in `workPath`, you can type:

    ``` shell
    CLSSPATH=$CLASSPATH:workPath/lib/*
    ```

- Then the `.jar` files will be included in your java classpath. After this, swith into `src` directory and type:

    ``` shell
    javac *.java
    ```

    to compile all the java files
    
- After that, please first fetch all the latest problems in [LeetCode](https://leetcode.com/problemset/algorithms/) by typing

    ``` shell
    java Crawler
    ```

- And then you can add the mail List to `config/sending_list.txt`, one address each line
- Also, please set your own host account in `config/account.txt`
- Finally, you can type

    ``` shell
    java Scheduler [hour:min:second] [period]
    ```

    There are two optional arguments, the first is the first time to send, say, 00:00:01(default). The second is the period of sending the mail, say, 86400000(default), which is one day.Also, you can set period to `now` for debugging.
- The sending strategy is various, the default stategy is as follows:
    - Monday, Tuesday, Wednesday - easy problem
    - Thursday, Friday - medium problem
    - Saturday, Sunday - hard problem

