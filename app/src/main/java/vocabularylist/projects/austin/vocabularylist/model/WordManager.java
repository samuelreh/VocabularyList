package vocabularylist.projects.austin.vocabularylist.model;

import android.app.Activity;
import android.os.SystemClock;

import java.util.*;

import vocabularylist.projects.austin.vocabularylist.providers.DatabaseIO;

/**
 * Created by Austin on 2016-12-21.
 * Using singleton design pattern
 */


public class WordManager implements Iterable<Word> {
    private static  WordManager instance;
    private Map<String, Word> wordList = new TreeMap<>();
    private List<String> offlineWords = new ArrayList<>();

    public static WordManager getInstance(){
        if(instance == null){
            instance = new WordManager();
        }
        return instance;
    }

    public ArrayList<String> getOfflineWords() {
        //return a copy of the offline words
        return new ArrayList<>(offlineWords);
    }

    public void addToOfflineWords(String word){
        if(!offlineWords.contains(word)) {
            offlineWords.add(word);
        }
    }

    public void removeOfflineWord(String word){
        offlineWords.remove(word);
    }

    public void clearOfflineWords(){
        offlineWords.clear();
    }

    public WordManager() {
    }



    public void addWord(Word w) {
        wordList.put(w.getTopLevelName(), w);
        System.out.println("Added word: " + w.getTopLevelName());
        if(offlineWords.contains(w.getTopLevelName())){
            offlineWords.remove(w.getTopLevelName());
        }
    }

    public void removeWord(Word w){
        if (hasWord(w)) {
            wordList.remove(w.getTopLevelName());
        }
    }

    public boolean hasWord(String w) {
        return wordList.containsKey(w);
    }

    public boolean hasWord(Word w){
        return wordList.values().contains(w);
    }

    public List<Word> getWords(){
        List<Word> bufferList = new ArrayList<>();
        bufferList.addAll(wordList.values());
        Collections.sort(bufferList);
        return bufferList;
    }

    /**
     * Returns the given word if it exists, null otherwise;
     * @return the desired word
     */
    public Word getWord(String word) {
        return (wordList.containsKey(word) ? wordList.get(word) : null);
    }

    @Override
    public Iterator<Word> iterator() {
        return wordList.values().iterator();
    }

    public Word getRandomWord() {
        Random random = new Random(SystemClock.currentThreadTimeMillis());
        int random_int = Math.abs(random.nextInt());
        random_int = random_int % wordList.size();
        return (Word) wordList.values().toArray()[random_int];
    }

}
