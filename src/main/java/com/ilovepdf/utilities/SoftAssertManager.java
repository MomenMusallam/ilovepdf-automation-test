package com.ilovepdf.utilities;

import org.testng.asserts.SoftAssert;

public final class SoftAssertManager {

    private static final ThreadLocal<SoftAssert> softAssertThreadLocal = ThreadLocal.withInitial(SoftAssert::new);

    private SoftAssertManager() {}

    public static SoftAssert get() {
        return softAssertThreadLocal.get();
    }

    public static void assertAll() {
        get().assertAll();
        softAssertThreadLocal.remove();
    }
}
