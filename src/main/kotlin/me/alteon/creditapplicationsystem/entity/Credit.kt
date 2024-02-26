package me.alteon.creditapplicationsystem.entity

import jakarta.persistence.*
import me.alteon.creditapplicationsystem.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "Credit")
data class Credit(
    @Column(nullable = false, unique = true) val creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false, unique = true) val creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false, unique = true) val dayFirstInstallment: LocalDate,
    @Column(nullable = false, unique = true) val numberOfInstallments: Int = 0,
    @Enumerated val status: Status = Status.IN_PROGRESS,
    @ManyToOne var customer: Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
