/*
 * Copyright 2021 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.dgs.plugin

import com.intellij.openapi.Disposable
import com.intellij.openapi.module.Module
import com.intellij.openapi.roots.ContentEntry
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.testFramework.PsiTestUtil
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase
import com.netflix.dgs.plugin.hints.DgsDataSimplifyingInspector

class DgsDataSimplifyingInspectorTest: DgsTestCase() {


    fun testSimplifyQuery() {
        myFixture.configureByFile("UsingDgsDataForQuery.java")
        myFixture.enableInspections(DgsDataSimplifyingInspector::class.java)
        myFixture.checkHighlighting(false, false, true, true)
        myFixture.launchAction(myFixture.findSingleIntention("@DgsData(parentType=\"Query\") can be simplified to @DgsQuery"))
        myFixture.checkResultByFile("FixedDgsDataForQuery.java")
    }



    override fun getTestDataPath() = "src/test/testData/dgsdata"
}

