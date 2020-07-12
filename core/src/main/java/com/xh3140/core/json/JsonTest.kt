package com.xh3140.core.json

import android.util.Log
import com.xh3140.core.json.tokenizer.CharReader

import com.xh3140.core.json.tokenizer.TokenList
import com.xh3140.core.json.tokenizer.Tokenizer
import java.io.StringReader


object JsonTest {

    private fun log(msg: String) = Log.d("xh3140", msg)

    fun test0() {
        val jsonObject = JsonObject()
        jsonObject["name"] = "xh3140"
        jsonObject["age"] = 24
        jsonObject["height"] = 1.66
        jsonObject["like"] = JsonArray().apply {
            add("game")
            add("computer")
            add("video")
            add("music")
        }
        jsonObject["friend"] = JsonObject().apply {
            this["name"] = "six"
            this["age"] = 6
            this["sex"] = false
        }

        val json = jsonObject.toJson()
        Log.d("xh3140", "json = \n${json}\n")
    }

    fun test1() {
        val json = """
{
  "k1": null,
  "k2": true,
  "k3": false,
  "k4": 999,
  "k5": 0.1234,
  "k6": -0.1234,
  "k7": 9e9,
  "k8": -8e8,
  "k9": 9E9,
  "k10": -8E8,
  "k11": 9E-9,
  "k12": -8E-8,
  "k13": "",
  "k14": "message",
  "k15": "name=\"xh3140\"",
  "k16": [],
  "k17": [null, 123, 0.123, -123, -0.123, true, false, "", "test", [], {}],
  "k18": {
    "k1": null,
    "k2": true,
    "k3": false,
    "k4": 999,
    "k5": [true, "obj"]
  },
  "k20": 0.123e456
}
"""
        val charReader = CharReader(StringReader(json))
        val tokens: TokenList = Tokenizer.tokenize(charReader)
        log(tokens.toString())
    }
}