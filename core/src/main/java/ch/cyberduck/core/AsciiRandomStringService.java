package ch.cyberduck.core;

/*
 * Copyright (c) 2002-2017 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import org.apache.commons.text.RandomStringGenerator;

public class AsciiRandomStringService implements RandomStringService {

    private int length;

    public AsciiRandomStringService() {
        this(8);
    }

    public AsciiRandomStringService(final int length) {
        this.length = length;
    }

    @Override
    public String random() {
        return new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(length);
    }
}
