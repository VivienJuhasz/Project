package hu.masterfield.datatypes;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

/**
 * A Saving típusú accountok megadásakor használt adatok osztályba szervezése.
 */
public class Saving {

    // fiók típusa
    // Saving
    // Money Market
    @CsvBindByName(column="accountTypes")
    private String accountTypes;

    // tulajdonos típus
    // Individual
    // Joint
    @CsvBindByName(column="ownershipTypes")
    private String ownershipTypes;

    // account name
    @CsvBindByName(column="accountName")
    private String accountName;

    // opening balance
    @CsvBindByName(column="openingBalance")
    private String openingBalance;

}
