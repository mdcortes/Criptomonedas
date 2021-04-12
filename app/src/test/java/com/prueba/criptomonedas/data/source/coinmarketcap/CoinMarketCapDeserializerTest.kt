package com.prueba.criptomonedas.data.source.coinmarketcap

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Clase de prueba de CoinMarketCapDeserializer
 */
class CoinMarketCapDeserializerTest {

    /**
     * Objeto Gson usado para deserializar
     */
    private lateinit var gson: Gson

    /**
     * Deserializador sobre el que se hacen las pruebas
     */
    private lateinit var deserializer: CoinMarketCapDeserializer

    /**
     * Crea instancias nuevas del deserializador y el objeto Gson antes de comenzar las pruebas
     */
    @Before
    fun createDeserializer(){
        gson = Gson()
        deserializer = CoinMarketCapDeserializer()
    }

    /**
     * Prueba que se logra deserializar desde un Json una criptomoneda satisfactoriamente
     */
    @Test
    fun deserialize_success_returnOne() {
        val rawJson = "{\"status\":{\"timestamp\":\"2021-04-12T10:18:30.601Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":19,\"credit_count\":1,\"notice\":null,\"total_count\":4668},\"data\":[{\"id\":4077,\"name\":\"Maya Preferred\",\"symbol\":\"MAYP\",\"slug\":\"maya-preferred\",\"num_market_pairs\":5,\"date_added\":\"2019-07-03T00:00:00.000Z\",\"tags\":[],\"max_supply\":null,\"circulating_supply\":0,\"total_supply\":250000000,\"platform\":{\"id\":1027,\"name\":\"Ethereum\",\"symbol\":\"ETH\",\"slug\":\"ethereum\",\"token_address\":\"0x7CDA79830Faf07Ed696Fe220566116951CED36A7\"},\"cmc_rank\":3475,\"last_updated\":\"2021-04-12T10:17:10.000Z\",\"quote\":{\"CLP\":{\"price\":150990473.19095054,\"volume_24h\":9763553.363746854,\"percent_change_1h\":-1.43263698,\"percent_change_24h\":31.37032013,\"percent_change_7d\":68.61310016,\"percent_change_30d\":38158.62698225,\"percent_change_60d\":20329.01484623,\"percent_change_90d\":15789.49259859,\"market_cap\":0,\"last_updated\":\"2021-04-12T10:18:09.000Z\"}}}]}"
        val jsonObject = gson.fromJson(rawJson, JsonObject::class.java)

        val coinMarketCapResponse = deserializer.deserialize(jsonObject, null, null)
        val infoList = coinMarketCapResponse.data

        assertEquals(1, infoList.size)
        val currencyInfo = infoList[0]

        assertEquals("MAYP", currencyInfo.code)
        assertEquals("Maya Preferred", currencyInfo.name)
        assertEquals(150990473.19095054.toBigDecimal(), currencyInfo.value)
    }

    /**
     * Prueba que logra deserializar satisfactoriamente un total de 10 criptomonedas
     */
    @Test
    fun deserialize_success_returnListOfTen() {
        val rawJson = "{\"status\":{\"timestamp\":\"2021-04-12T09:39:20.899Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":236,\"credit_count\":1,\"notice\":null,\"total_count\":4668},\"data\":[{\"id\":4077,\"name\":\"Maya Preferred\",\"symbol\":\"MAYP\",\"slug\":\"maya-preferred\",\"num_market_pairs\":5,\"date_added\":\"2019-07-03T00:00:00.000Z\",\"tags\":[],\"max_supply\":null,\"circulating_supply\":0,\"total_supply\":250000000,\"platform\":{\"id\":1027,\"name\":\"Ethereum\",\"symbol\":\"ETH\",\"slug\":\"ethereum\",\"token_address\":\"0x7CDA79830Faf07Ed696Fe220566116951CED36A7\"},\"cmc_rank\":3475,\"last_updated\":\"2021-04-12T09:38:10.000Z\",\"quote\":{\"CLP\":{\"price\":152718950.40892515,\"volume_24h\":9875034.806245018,\"percent_change_1h\":-0.70232386,\"percent_change_24h\":32.49497094,\"percent_change_7d\":70.89948115,\"percent_change_30d\":38656.66009159,\"percent_change_60d\":20593.39481804,\"percent_change_90d\":15857.24402882,\"market_cap\":0,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}},{\"id\":8490,\"name\":\"Punk\",\"symbol\":\"PUNK\",\"slug\":\"punk\",\"num_market_pairs\":1,\"date_added\":\"2021-02-17T00:00:00.000Z\",\"tags\":[\"collectibles-nfts\"],\"max_supply\":null,\"circulating_supply\":0,\"total_supply\":0,\"platform\":{\"id\":1027,\"name\":\"Ethereum\",\"symbol\":\"ETH\",\"slug\":\"ethereum\",\"token_address\":\"0x9cea2ed9e47059260c97d697f82b8a14efa61ea5\"},\"cmc_rank\":3430,\"last_updated\":\"2021-04-12T09:39:05.000Z\",\"quote\":{\"CLP\":{\"price\":116519105.25333872,\"volume_24h\":13736222.249909041,\"percent_change_1h\":-0.36683758,\"percent_change_24h\":-0.339579,\"percent_change_7d\":35.08494176,\"percent_change_30d\":14.18245194,\"percent_change_60d\":null,\"percent_change_90d\":null,\"market_cap\":0,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}},{\"id\":1691,\"name\":\"Project-X\",\"symbol\":\"NANOX\",\"slug\":\"project-x\",\"num_market_pairs\":1,\"date_added\":\"2017-05-30T00:00:00.000Z\",\"tags\":[],\"max_supply\":null,\"circulating_supply\":0.078264,\"total_supply\":1.130085,\"platform\":null,\"cmc_rank\":2334,\"last_updated\":\"2021-04-12T09:39:07.000Z\",\"quote\":{\"CLP\":{\"price\":113171233.95871386,\"volume_24h\":1196350.089859994,\"percent_change_1h\":-0.62111332,\"percent_change_24h\":-10.12125817,\"percent_change_7d\":-5.41272173,\"percent_change_30d\":722.16505678,\"percent_change_60d\":864.68412419,\"percent_change_90d\":758.32986664,\"market_cap\":8857233.454544783,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}},{\"id\":93,\"name\":\"42-coin\",\"symbol\":\"42\",\"slug\":\"42-coin\",\"num_market_pairs\":1,\"date_added\":\"2014-01-14T00:00:00.000Z\",\"tags\":[\"mineable\",\"hybrid-pow-pos\",\"scrypt\",\"store-of-value\"],\"max_supply\":null,\"circulating_supply\":41.99995206,\"total_supply\":41.99995206,\"platform\":null,\"cmc_rank\":1215,\"last_updated\":\"2021-04-12T09:39:03.000Z\",\"quote\":{\"CLP\":{\"price\":93606476.41780642,\"volume_24h\":4006356.796242857,\"percent_change_1h\":-0.62111378,\"percent_change_24h\":0.84389488,\"percent_change_7d\":2.25183488,\"percent_change_30d\":-8.08245963,\"percent_change_60d\":-20.31989582,\"percent_change_90d\":-2.17649848,\"market_cap\":3931467522.0533895,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}},{\"id\":5936,\"name\":\"Robonomics Web Services\",\"symbol\":\"RWS\",\"slug\":\"robonomics-web-services\",\"num_market_pairs\":2,\"date_added\":\"2020-07-28T00:00:00.000Z\",\"tags\":[\"iot\",\"polkadot-ecosystem\"],\"max_supply\":null,\"circulating_supply\":0,\"total_supply\":100,\"platform\":{\"id\":1027,\"name\":\"Ethereum\",\"symbol\":\"ETH\",\"slug\":\"ethereum\",\"token_address\":\"0x08ad83d779bdf2bbe1ad9cc0f78aa0d24ab97802\"},\"cmc_rank\":3278,\"last_updated\":\"2021-04-12T09:38:08.000Z\",\"quote\":{\"CLP\":{\"price\":86441344.14440465,\"volume_24h\":30726760.861217882,\"percent_change_1h\":-0.32396882,\"percent_change_24h\":11.30309588,\"percent_change_7d\":37.82562175,\"percent_change_30d\":81.94693432,\"percent_change_60d\":155.14313839,\"percent_change_90d\":224.55349968,\"market_cap\":0,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}},{\"id\":5157,\"name\":\"3X Long Bitcoin Token\",\"symbol\":\"BULL\",\"slug\":\"3x-long-bitcoin-token\",\"num_market_pairs\":6,\"date_added\":\"2020-01-21T00:00:00.000Z\",\"tags\":[],\"max_supply\":null,\"circulating_supply\":0,\"total_supply\":454.930863,\"platform\":{\"id\":1839,\"name\":\"Binance Chain\",\"symbol\":\"BNB\",\"slug\":\"binance-coin\",\"token_address\":\"BULL-BE4\"},\"cmc_rank\":2466,\"last_updated\":\"2021-04-12T09:39:02.000Z\",\"quote\":{\"CLP\":{\"price\":60230807.93154064,\"volume_24h\":3880299575.386517,\"percent_change_1h\":-1.94489977,\"percent_change_24h\":2.28584398,\"percent_change_7d\":12.7498574,\"percent_change_30d\":-3.76255858,\"percent_change_60d\":35.94719322,\"percent_change_90d\":66.12713269,\"market_cap\":0,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}},{\"id\":7095,\"name\":\"Unisocks\",\"symbol\":\"SOCKS\",\"slug\":\"unisocks\",\"num_market_pairs\":3,\"date_added\":\"2020-09-18T00:00:00.000Z\",\"tags\":[\"collectibles-nfts\",\"defi\"],\"max_supply\":315,\"circulating_supply\":314,\"total_supply\":314,\"platform\":{\"id\":1027,\"name\":\"Ethereum\",\"symbol\":\"ETH\",\"slug\":\"ethereum\",\"token_address\":\"0x23b608675a2b2fb1890d3abbd85c5775c51691d5\"},\"cmc_rank\":781,\"last_updated\":\"2021-04-12T09:38:10.000Z\",\"quote\":{\"CLP\":{\"price\":57742887.76947719,\"volume_24h\":88950587.03299785,\"percent_change_1h\":0.23298429,\"percent_change_24h\":-1.5294605,\"percent_change_7d\":7.2632148,\"percent_change_30d\":-18.80799863,\"percent_change_60d\":194.68837876,\"percent_change_90d\":2048.62090752,\"market_cap\":18131266759.615837,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}},{\"id\":8482,\"name\":\"mStable BTC\",\"symbol\":\"MBTC\",\"slug\":\"mstable-btc\",\"num_market_pairs\":1,\"date_added\":\"2021-02-15T00:00:00.000Z\",\"tags\":[\"defi\"],\"max_supply\":21000000,\"circulating_supply\":0,\"total_supply\":12.519264,\"platform\":{\"id\":1027,\"name\":\"Ethereum\",\"symbol\":\"ETH\",\"slug\":\"ethereum\",\"token_address\":\"0x945facb997494cc2570096c74b5f66a3507330a1\"},\"cmc_rank\":4614,\"last_updated\":\"2021-04-12T09:39:04.000Z\",\"quote\":{\"CLP\":{\"price\":47212435.86009633,\"volume_24h\":1e-8,\"percent_change_1h\":-0.36683758,\"percent_change_24h\":1.52697326,\"percent_change_7d\":8.26285741,\"percent_change_30d\":16.46370215,\"percent_change_60d\":null,\"percent_change_90d\":null,\"market_cap\":0,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}},{\"id\":8892,\"name\":\"Klondike BTC\",\"symbol\":\"KBTC\",\"slug\":\"klondike-btc\",\"num_market_pairs\":1,\"date_added\":\"2021-03-19T00:00:00.000Z\",\"tags\":[\"yield-farming\",\"seigniorage\"],\"max_supply\":187,\"circulating_supply\":0,\"total_supply\":0,\"platform\":{\"id\":1027,\"name\":\"Ethereum\",\"symbol\":\"ETH\",\"slug\":\"ethereum\",\"token_address\":\"0xE6C3502997f97F9BDe34CB165fBce191065E068f\"},\"cmc_rank\":2662,\"last_updated\":\"2021-04-12T09:39:07.000Z\",\"quote\":{\"CLP\":{\"price\":46747372.99571889,\"volume_24h\":697911551.5657709,\"percent_change_1h\":-0.39772084,\"percent_change_24h\":-1.20562074,\"percent_change_7d\":10.60669087,\"percent_change_30d\":null,\"percent_change_60d\":null,\"percent_change_90d\":null,\"market_cap\":0,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}},{\"id\":5434,\"name\":\"pTokens BTC\",\"symbol\":\"PBTC\",\"slug\":\"ptokens-btc\",\"num_market_pairs\":2,\"date_added\":\"2020-04-12T00:00:00.000Z\",\"tags\":[\"defi\"],\"max_supply\":21000000,\"circulating_supply\":0,\"total_supply\":21000000,\"platform\":{\"id\":1027,\"name\":\"Ethereum\",\"symbol\":\"ETH\",\"slug\":\"ethereum\",\"token_address\":\"0x5228a22e72ccc52d415ecfd199f99d0665e7733b\"},\"cmc_rank\":4245,\"last_updated\":\"2021-04-12T09:39:04.000Z\",\"quote\":{\"CLP\":{\"price\":45769975.98219768,\"volume_24h\":1e-8,\"percent_change_1h\":-0.51874011,\"percent_change_24h\":1.0840238,\"percent_change_7d\":4.9754798,\"percent_change_30d\":13.31498295,\"percent_change_60d\":46.64436405,\"percent_change_90d\":81.70289179,\"market_cap\":0,\"last_updated\":\"2021-04-12T09:39:09.000Z\"}}}]}"
        val jsonObject = gson.fromJson(rawJson, JsonObject::class.java)

        val coinMarketCapResponse = deserializer.deserialize(jsonObject, null, null)
        val infoList = coinMarketCapResponse.data

        assertEquals(10, infoList.size)
    }
}