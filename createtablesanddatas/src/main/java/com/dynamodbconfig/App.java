package com.dynamodbconfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // System.out.println("teste");
        System.out.println("antes de tentar criar tabelas e dados");
        try {
            HeroesTable.create();
            HeroesData.create();
        } catch (Exception e) {
            System.out.println("tabela e/ou dados não criados");
            e.getStackTrace();
        }
    }
}
