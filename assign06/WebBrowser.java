/**
 *
 * Simulates a Web Browser, allowing users to move forward and back between pages, as well as visit new pages. The forward
 * and backward webpages are stored in two seperate LinkedListStacks
 *
 * @Author Isaac Hall and Bradley Bartelt
 * @Version 3/2/2023
 */
package assign06;

import java.net.URL;
import java.util.NoSuchElementException;
public class WebBrowser{
    private LinkedListStack<URL> backStack;
    private LinkedListStack<URL> forwardStack;
    private URL currentPage;

    public WebBrowser(){
        currentPage = null;
        backStack = new LinkedListStack<URL>();
        forwardStack = new LinkedListStack<URL>();
    }

    /**
     * Constructs a new WebBrowser using a provided history
     * @param history
     */
    public WebBrowser(SinglyLinkedList<URL> history){
        backStack = new LinkedListStack<URL>();
        // adds the URLS from history to the backStack
        for(int i = history.size() - 1; i > 0; i--){
            backStack.push(history.get(i));
        }
        // sets currentPage to the head element of history
        currentPage = history.get(0);
        forwardStack = new LinkedListStack<URL>();
    }

    /**
     * pass in the Url of the next webpage visited
     * @param webpage
     */
    public void visit(URL webpage){
        forwardStack.clear();
        //adds the current page to the top of the backStack
        if(currentPage != null)
            backStack.push(currentPage);
        // sets the current page to the given webpage
        currentPage = webpage;
    }

    /**
     * simulates a user returning to the previous webpage
     * @return URL
     * @throws NoSuchElementException
     */
    public URL back() throws NoSuchElementException{
        if(backStack.isEmpty()){
            throw new NoSuchElementException();
        }
        // adds the current page to the forward stack and then sets the current page to the backStacks most recent
        // and deletes it
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        return currentPage;
    }

    /**
     * Simulates the user moving back to the webpage they were previously on before going back.
     * @return
     * @throws NoSuchElementException
     */
    public URL forward() throws NoSuchElementException{
        if(forwardStack.isEmpty()){
            throw new NoSuchElementException();
        }
        // adds the current page to the back stack and then sets the current page to the fowardStacks most recent
        // and deletes it
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        return currentPage;
    }

    /**
     * returns a singlyLinked list filled with the users previously visited pages from most recent to least recently visited
     * @return
     */
    public SinglyLinkedList<URL> history(){

        SinglyLinkedList<URL> visited = new SinglyLinkedList<URL>();
        int tempSize = backStack.size();
        // goes to the beginning of the backStack
        for(int i = 0; i < tempSize; i++){
            back();
        }
        visited.insertFirst(currentPage);
        // inserts the values in order into visited
        for(int i = 0; i < tempSize; i++){
            visited.insertFirst(forward());
        }
        return visited;
    }
}
