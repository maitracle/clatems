package com.clatems.clatems.klays

import com.klaytn.caver.utils.ChainId
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import xyz.groundx.caver_ext_kas.CaverExtKAS


@RequiredArgsConstructor
@Component
class KlayService {
    private val caver: CaverExtKAS
    private val CONTRACT_ALIAS = "my-first-kip17"
    private val _CONTRACT_ALIAS = "clatems-contract"

    companion object {
        const val ACCESS_KEY = "KASKM4Q3XYN0D0PIEC7G52XG"
        const val SECRET_ACCESS_KEY = "96_HAMa3WbPwXv-JA4-dqBtgzarAyfSTHCcvb_9h"
    }

    init {
        this.caver = CaverExtKAS()
        this.caver.initKASAPI(ChainId.BAOBAB_TESTNET, ACCESS_KEY, SECRET_ACCESS_KEY)
    }

    fun deployContract() {
        val name = "My First KIP-17"
        val symbol = "MFK"
        this.caver.kas.kip17.deploy(name, symbol, this.CONTRACT_ALIAS)
    }

    fun mintToken(imageMetaUrl: String, artworkId: Long): String {
        val clatemsWalletAddress = "0xc32c196a843125cdb462108f283bdfbaeb7602c1"
        val id = "0x${artworkId + 99}"

        val response = caver.kas.kip17.mint(this.CONTRACT_ALIAS, clatemsWalletAddress, id, imageMetaUrl)

        return response.transactionHash
    }
}
