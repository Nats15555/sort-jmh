/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;


@Fork(value = 1)
@Warmup(iterations = 3)
@State(Scope.Benchmark)
public class MyBenchmark {

    private final int size = 600000;
    private static float[] temperatures;
    public static final float LEFT_LIMIT = -272F;
    public static final float RIGHT_LIMIT = 499;

    public SortTemperatures setup() {
        temperatures = new float[size];
        float generatedFloat;
        int randomNum;
        for (int i = 0; i < temperatures.length; i++) {
            generatedFloat = (float) Math.ceil(LEFT_LIMIT + new Random().nextFloat() * (RIGHT_LIMIT - LEFT_LIMIT));
            randomNum = (int) (Math.random() * 9);
            temperatures[i] = (float) (generatedFloat + (randomNum / 10.));
        }
        return new SortTemperatures(temperatures);
    }

    @Benchmark
    public void sortTemperaturesCollectionSort(Blackhole bh) {
        List<Float> result = setup().sortTemperaturesCollectionSort();
        bh.consume(result);
    }

    @Benchmark
    public void mySortTemperatures(Blackhole bh) {
        List<Float> result = setup().mySortTemperatures();
        bh.consume(result);
    }

    @Benchmark
    public void sortTemperaturesBobbleSort(Blackhole bh) {
        List<Float> result = setup().sortTemperaturesBobbleSort();
        bh.consume(result);
    }


}
