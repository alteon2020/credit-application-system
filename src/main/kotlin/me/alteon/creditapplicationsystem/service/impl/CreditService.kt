package me.alteon.creditapplicationsystem.service.impl

import me.alteon.creditapplicationsystem.entity.Credit
import me.alteon.creditapplicationsystem.repository.CreditRepository
import me.alteon.creditapplicationsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(private val creditRepository: CreditRepository, private val customerService: CustomerService) :
    ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> = this.creditRepository.findAllByCustomer(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit? {
        val credit: Credit = this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("Credit Code $creditCode not found")
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact Admin")
    }
}