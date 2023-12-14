package presentation.GET;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import presentation.dto.DatoInforme;

public class GetRankingsHandler implements Handler {

    public void handle(@NotNull Context context) throws Exception {

        try {
            context.status(200).json(new DatoInforme());
        } catch (Exception e){
            System.out.println(e.getMessage());
            context.status(501);
        }

    }

}
