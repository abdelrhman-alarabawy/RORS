
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author abdelrhman
 */
public class test111 {

    public void check() {
        String email = "abdelrhman@yahoo.com";
        StringTokenizer em = new StringTokenizer(email, "@");
        String part1 = em.nextToken();
        String part2 = em.nextToken();
        System.err.println(part1);
        System.err.println(part2);

        try {
            if ((email.indexOf('@') == -1) || (part1.length() < 8)) {
                throw new Exception("Invalid Email");
            }
        } catch (Exception e) {

        }

    }
}
