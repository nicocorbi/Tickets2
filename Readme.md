# Proyecto Tickets
Creación de un proyecto en android estudio denominado Tickets.
A traves del uso de fragmentos en tu proyecto, deberas crear una serie de errores que tendran que mostrarse y posteriormente resolverse.
El uso de fragmentos es importante ya que nos permite reutilizar, tambien aprenderemos el ciclo de vida de las activities y montar informacion de una actividad al hacer uso de bundle.
## ¿ En que consiste la practica?
la practica consistira en crear una serie de fragmentos y una UNICA activity , la cual debera mostrar una lista de fragmentos y estos  nos permitiran tanto borrar como crear o editar un ticket.
## codigo de ejemplo
```java
public class TestFragment extends Fragment {



    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }
    @Override
    public void onStart(){
        super.onStart();
        if(getActivity() instanceof MainActivity){// instanceof pregunta si es una instancia de mainActivity y evita los nulos
            ((MainActivity) getActivity()).test();//llamo a una funcion del mainactivity
            // con un else if(getActivity()) instanceof SecondActivity podemos hacer que ese fragmento haga cosas distintas dependendiendo de la activity
        }
    }
}
```
```java
public class MainActivity extends AppCompatActivity {

    TestFragment fragmentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transact = fragmentManager.beginTransaction();

        fragmentMain = new TestFragment();
        transact.replace(R.id.fragmentContainer,fragmentMain);
        transact.commit();
    }
    void test(){

    }
}
```

