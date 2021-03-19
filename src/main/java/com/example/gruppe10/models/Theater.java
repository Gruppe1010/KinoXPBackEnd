
import com.example.gruppe10.models.Calendar;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="theaters")
public class Theater {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_theater")
    private int id;

    @NotNull
    private int rows;

    @NotNull
    private int seatsPrRow;

    @NotNull
    @JoinColumn(name="id_calendar", nullable = false)
    private Calendar calendar;







}







