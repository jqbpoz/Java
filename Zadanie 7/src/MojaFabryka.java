public class MojaFabryka implements ThreadFactory{

    @Override
    public Thread getThread(Runnable run) {
        return new Thread(run);
    }

}

