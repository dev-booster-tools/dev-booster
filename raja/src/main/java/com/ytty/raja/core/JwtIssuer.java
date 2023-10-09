package com.ytty.raja.core;

import java.util.List;

interface JwtIssuer {

    String issue(long userId, String email, List<String> roles);
}
