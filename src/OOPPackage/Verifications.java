/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOPPackage;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Regex is beautiful, isn't it?
 * @author Thomas Sund Mjåland
 */
public class Verifications {
    public static final Predicate<String> MAILADDRESS = Pattern.compile("^[^@ ]+?@[a-zA-Z]+?\\.[a-zA-Z]+$").asPredicate();
    public static final Predicate<String> NAME = Pattern.compile("^[a-zA-Z\\-]+$").asPredicate();
    public static final Predicate<String> EMPLOYEEID = Pattern.compile("^0\\d{4}$").asPredicate();
    public static final Predicate<String> FLIGHTID = Pattern.compile("^[a-zA-Z]{2,3}\\d{3,4}$").asPredicate();
    public static final Predicate<String> CERTIFICATENUMBER = Pattern.compile("^0\\d{6}$").asPredicate();
    public static final Predicate<String> SEAT = Pattern.compile("^\\d+[a-zA-Z]$").asPredicate();
    public static final Predicate<String> NUMERIC = Pattern.compile("^\\d+$").asPredicate();
    public static final Predicate<String> YESNO = Pattern.compile("^([yYnN]|[yY][eE][sS]|[nN][oO])$").asPredicate();
    public static final Predicate<String> YES = Pattern.compile("^([yY]|[yY][eE][sS])$").asPredicate();
    public static final Predicate<String> NO = Pattern.compile("^([nN]|[nN][oO])$").asPredicate();
    //public static final Predicate<String> SOMETHING = Pattern.compile("^$").asPredicate();
}
