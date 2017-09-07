package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickCutSorter extends AbstractQuickSorter{

    final private InsertionSorter cut = new InsertionSorter();

    public QuickCutSorter() {
        super(SorterType.QUICK_CUT);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1, 5);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int m) {
        sort(comparator, list, 0, list.size()-1, m);
    }


    private <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi, int m) {
        if(hi - lo <= m) {
            cut.sort(comparator,list,lo,hi);
            return;
        }
        int i = partition(comparator, list, lo, hi);
        sort(comparator, list, lo, i-1, m);
        sort(comparator, list, i+1, hi, m);
    }

    @Override
    public void addSorterListener(@NotNull SorterListener sorterListener) {
        super.addSorterListener(sorterListener);
        cut.addSorterListener(sorterListener);
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener sorterListener) {
        super.removeSorterListener(sorterListener);
        cut.removeSorterListener(sorterListener);
    }

    // Alternative implementation
    /*
    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if(hi - lo <= m){
            cut.sort(comparator, list);
            return;
        }
        int i = partition(comparator, list, lo, hi);
        sort(comparator, list, lo, i-1);
        sort(comparator, list, i+1, hi);
    }

    */
}
