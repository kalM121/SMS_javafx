package com.example.studentmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
   public Connection databaseLink;

       public  Connection getConnection(){
           String databaseName="studentrecord";
           String databaseUser="root";
           String databasePassword="1234";
           String url="jdbc:mysql://127.0.0.1/"+ databaseName;
            try{

                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

               }catch(Exception e){ e.printStackTrace();
                e.getCause();
            }
            return databaseLink;
        }

    }

