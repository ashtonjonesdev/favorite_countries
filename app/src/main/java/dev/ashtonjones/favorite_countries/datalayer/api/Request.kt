/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package dev.ashtonjones.favorite_countries.datalayer.api

import com.google.gson.Gson
import dev.ashtonjones.favorite_countries.datamodels.CountryResult
import java.net.URL


class Request(private val url: String) {


//  fun run() {
//    val repoListJsonStr = URL(url).readText()
//    Log.d(javaClass.simpleName, repoListJsonStr)
//
//  }

  companion object {
    private const val URL = "https://api.worldbank.org/v2/countries?format=json"
//    private const val SEARCH = "q=super+mario+language:kotlin&sort=stars&order=desc"
//    private const val COMPLETE_URL = "$URL?$SEARCH"
  }

  fun run(): CountryResult {

    // Execute the request
    val repoListJsonStr = URL(URL).readText()

    // Use Gson library to parse Json response to a List of Countries (CountryResult object)
    return Gson().fromJson(repoListJsonStr, CountryResult::class.java)
  }


}