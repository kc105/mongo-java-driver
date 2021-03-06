/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.bson.codecs


import org.bson.BsonDocument
import org.bson.BsonDocumentWrapper
import org.bson.RawBsonDocument
import spock.lang.Specification

import static org.bson.codecs.configuration.CodecRegistries.fromProviders

class BsonCodecProviderSpecification extends Specification {

    def provider = new BsonCodecProvider()
    def codecRegistry = fromProviders(provider)

    def 'should get correct codec'() {
        expect:
        provider.get(String, codecRegistry) == null

        provider.get(BsonDocument, codecRegistry).class == BsonCodec
        provider.get(BsonDocumentWrapper, codecRegistry).class == BsonCodec
        provider.get(RawBsonDocument, codecRegistry).class == BsonCodec
        provider.get(BsonDocumentSubclass, codecRegistry).class == BsonCodec
    }
}
