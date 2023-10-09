package com.ytty.raja.core;

import com.auth0.jwt.interfaces.DecodedJWT;

interface JwtDecoder {

    DecodedJWT decode(String token);
}
