package io.emeraldpay.pjc.json

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class BlockResponseJsonSpec extends Specification {

    ObjectMapper objectMapper = JsonSpecCommons.objectMapper

    def "Deserialize"() {
        setup:
        InputStream json = BlockJsonSpec.classLoader.getResourceAsStream("blocks/0x401a1-full.json")
        when:
        def act = objectMapper.readValue(json, BlockResponseJson)
        then:
        act != null
        act.block != null
        act.justification == null
        with(act.block) {
            extrinsics.size() == 3
            with(header) {
                number == 0x401a1
            }
        }
    }
}