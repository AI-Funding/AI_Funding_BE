import com.AiFunding.ToBi.dto.ai.page.StuckInfoResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StuckInfoDtoTest {
    @Test
    public void rombokTest(){
        String stuckName = "한서진";
        Long currentprice = Long.valueOf(10000);
        Long priceFluc = Long.valueOf(2000);
        Double rateFluc = 2.73;
        String fluctuation = "UP";



        StuckInfoResponseDto dto = new StuckInfoResponseDto(
                stuckName,
                currentprice,
                priceFluc,
                rateFluc,
                fluctuation);

        Assertions.assertThat(dto.getStuckName()).isEqualTo(stuckName);
        Assertions.assertThat(dto.getCurrentPrice()).isEqualTo(currentprice);
        Assertions.assertThat(dto.getPriceFluc()).isEqualTo(priceFluc);
        Assertions.assertThat(dto.getRateFluc()).isEqualTo(rateFluc);
        Assertions.assertThat(dto.getFluctuation()).isEqualTo(fluctuation);
    }
}