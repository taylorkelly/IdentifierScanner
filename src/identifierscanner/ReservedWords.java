package identifierscanner;

import java.util.HashMap;

/**
 * Holds all of the reserved words for the source code.
 * Currently initializes the reserved words during class initialization and is
 * stored statically.
 */
public class ReservedWords {
    private static final HashMap<String, Token> reservedWords = new HashMap<String, Token>();

    static {
        // Basic C keywords
        reservedWords.put("auto", new Token("auto", "AUTO"));
        reservedWords.put("break", new Token("break", "BREAK"));
        reservedWords.put("case", new Token("case", "CASE"));
        reservedWords.put("char", new Token("char", "TYPE"));
        reservedWords.put("const", new Token("const", "CONST"));
        reservedWords.put("continue", new Token("continue", "CONTINUE"));
        reservedWords.put("default", new Token("default", "DEFAULT"));
        reservedWords.put("do", new Token("do", "DO"));
        reservedWords.put("double", new Token("double", "TYPE"));
        reservedWords.put("else", new Token("else", "ELSE"));
        reservedWords.put("entry", new Token("entry", "ENTRY"));
        reservedWords.put("enum", new Token("enum", "ENUM"));
        reservedWords.put("extern", new Token("extern", "EXTERN"));
        reservedWords.put("float", new Token("float", "FLOAT"));
        reservedWords.put("for", new Token("for", "FOR"));
        reservedWords.put("goto", new Token("goto", "GOTO"));
        reservedWords.put("if", new Token("if", "IF"));
        reservedWords.put("int", new Token("int", "TYPE"));
        reservedWords.put("long", new Token("long", "TYPE"));
        reservedWords.put("register", new Token("register", "REGISTER"));
        reservedWords.put("return", new Token("return", "RETURN"));
        reservedWords.put("short", new Token("short", "TYPE"));
        reservedWords.put("signed", new Token("signed", "SIGNED"));
        reservedWords.put("sizeof", new Token("sizeof", "SIZEOF"));
        reservedWords.put("static", new Token("static", "STATIC"));
        reservedWords.put("struct", new Token("struct", "STRUCT"));
        reservedWords.put("switch", new Token("switch", "SWITCH"));
        reservedWords.put("typedef", new Token("typedef", "TYPEDEF"));
        reservedWords.put("union", new Token("union", "UNION"));
        reservedWords.put("unsigned", new Token("unsigned", "UNSIGNED"));
        reservedWords.put("void", new Token("void", "TYPE"));
        reservedWords.put("volatile", new Token("volatile", "VOLATILE"));
        reservedWords.put("while", new Token("while", "WHILE"));

        // ISO C Keywords
        reservedWords.put("inline", new Token("inline", "ISO_INLINE"));
        reservedWords.put("restrict", new Token("restrict", "ISO_RESTRICT"));
        reservedWords.put("_Bool", new Token("_Bool", "ISO_BOOL"));
        reservedWords.put("_Complex", new Token("_Complex", "ISO_COMPLEX"));
        reservedWords.put("_Imaginary", new Token("_Imaginary", "ISO_IMAGINARY"));

        // GNU C Keywords
        reservedWords.put("__FUNCTION__", new Token("__FUNCTION__", "GNU_FUNCTION"));
        reservedWords.put("__PRETTY_FUNCTION__", new Token("__PRETTY_FUNCTION__", "GNU_FUNCTION"));
        reservedWords.put("__alignof", new Token("__alignof", "GNU_ALIGNOF"));
        reservedWords.put("__alignof__", new Token("__alignof", "GNU_ALIGNOF"));
        reservedWords.put("__asm", new Token("__asm", "GNU_ASM"));
        reservedWords.put("__asm__", new Token("__asm__", "GNU_ASM"));
        reservedWords.put("__attribute", new Token("__attribute", "GNU_ATTRIBUTE"));
        reservedWords.put("__attribute__", new Token("__attribute__", "GNU_ATTRIBUTE"));
        reservedWords.put("__builtin_offsetof", new Token("__builtin_offsetof", "GNU_BUILTINOFFSET"));
        reservedWords.put("__builtin_va_arg", new Token("__builtin_va_arg", "GNU_BUILTINVARG"));
        reservedWords.put("__complex", new Token("__complex", "GNU_COMPLEX"));
        reservedWords.put("__complex__", new Token("__complex__", "GNU_COMPLEX"));
        reservedWords.put("__const", new Token("__const", "GNU_CONST"));
        reservedWords.put("__extension__", new Token("__extension__", "GNU_EXTENSION"));
        reservedWords.put("__func__", new Token("__func__", "GNU_FUNCTION"));
        reservedWords.put("__imag", new Token("__imag", "GNU_IMAG"));
        reservedWords.put("__imag__", new Token("__imag__", "GNU_IMAG"));
        reservedWords.put("__inline", new Token("__inline", "GNU_INLINE"));
        reservedWords.put("__inline__", new Token("__inline__", "GNU_INLINE"));
        reservedWords.put("__label__", new Token("__label__", "GNU_LABEL"));
        reservedWords.put("__null", new Token("__null", "GNU_NULL"));
        reservedWords.put("__real", new Token("__real", "GNU_REAL"));
        reservedWords.put("__real__", new Token("__real__", "GNU_REAL"));
        reservedWords.put("__restrict", new Token("__restrict", "GNU_RESTRICT"));
        reservedWords.put("__restrict__", new Token("__restrict__", "GNU_RESTRICT"));
        reservedWords.put("__signed", new Token("__signed", "GNU_SIGNED"));
        reservedWords.put("__signed__", new Token("__signed__", "GNU_SIGNED"));
        reservedWords.put("__thread", new Token("__thread", "GNU_THREAD"));
        reservedWords.put("__typeof", new Token("__typeof", "GNU_TYPEOF"));
        reservedWords.put("__volatile", new Token("__volatile", "GNU_VOLATILE"));
        reservedWords.put("__volatile__", new Token("__volatile__", "GNU_VOLATILE"));

        // GNU C Attributes
        reservedWords.put("__const__", new Token("__const__", "GNU_ATTRIBUTE"));
        reservedWords.put("__format__", new Token("__format__", "GNU_ATTRIBUTE"));
        reservedWords.put("__malloc__", new Token("__malloc__", "GNU_ATTRIBUTE"));
        reservedWords.put("__mode__", new Token("__mode__", "GNU_ATTRIBUTE"));
        reservedWords.put("__noreturn__", new Token("__noreturn__", "GNU_ATTRIBUTE"));
        reservedWords.put("__nothrow__", new Token("__nothrow__", "GNU_ATTRIBUTE"));
        reservedWords.put("__nonnull__", new Token("__nonnull__", "GNU_ATTRIBUTE"));
        reservedWords.put("__pure__", new Token("__pure__", "GNU_ATTRIBUTE"));
        reservedWords.put("__warn_unused_result__", new Token("__warn_unused_result__", "GNU_ATTRIBUTE"));

        // C Machine Modes
        reservedWords.put("__DI__", new Token("__DI__", "MACHINE_MODE"));
        reservedWords.put("__HI__", new Token("__HI__", "MACHINE_MODE"));
        reservedWords.put("__QI__", new Token("__QI__", "MACHINE_MODE"));
        reservedWords.put("__SI__", new Token("__SI__", "MACHINE_MODE"));
        reservedWords.put("__word__", new Token("__word__", "MACHINE_MODE"));

        // GNU C Formats
        reservedWords.put("__printf__", new Token("__printf__", "ATTR_FORMAT"));
        reservedWords.put("__scanf__", new Token("__scanf__", "ATTR_FORMAT"));
    }

    /**
     * Checks if a token has a value of one of the reserved words.
     * If it is one of the reserved words, it returns the token representing
     * that reserved word.
     * If not, it returns the original token
     * @param token The token that should be checked against reserved words
     * @return The valid token that should be used for that value
     */
    public static Token checkReservedWords(Token token) {
        if (reservedWords.containsKey(token.getValue())) {
            return reservedWords.get(token.getValue());
        }
        return token;
    }
}
