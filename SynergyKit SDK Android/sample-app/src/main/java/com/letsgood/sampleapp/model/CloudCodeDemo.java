/*
 * Copyright [2015] [Letsgood.com s.r.o.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.Copyright [2015] [Letsgood.com s.r.o.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by Bc. Pavel Stambrecht for Letsgood.com s.r.o.
 */

package com.letsgood.sampleapp.model;


import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.resources.SynergykitCloudCode;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;

/**
 * Created by Marek on 1/14/15.
 */
public class CloudCodeDemo extends SynergykitCloudCode {
    @Expose
    private String path;
    @Expose
    private int age;
    @Expose
    private int ageRange;
    @Expose
    private String race;
    @Expose
    private float raceConfidence;
    @Expose
    private String gender;
    @Expose
    private float genderConfidence;
    @Expose
    private float smiling;
    @Expose
    private String glass;
    @Expose
    private float glassConfidence;
    @Expose
    private String name;

    public CloudCodeDemo(String cloudCodeName) {
        super(cloudCodeName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(int ageRange) {
        this.ageRange = ageRange;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public float getRaceConfidence() {
        return raceConfidence;
    }

    public void setRaceConfidence(float raceConfidence) {
        this.raceConfidence = raceConfidence;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getGenderConfidence() {
        return genderConfidence;
    }

    public void setGenderConfidence(float genderConfidence) {
        this.genderConfidence = genderConfidence;
    }

    public float getSmiling() {
        return smiling;
    }

    public void setSmiling(float smiling) {
        this.smiling = smiling;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public float getGlassConfidence() {
        return glassConfidence;
    }

    public void setGlassConfidence(float glassConfidence) {
        this.glassConfidence = glassConfidence;
    }
}
