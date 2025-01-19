package studentstats;

import itertools.DoubleEndedIterator;

import studentapi.*;

/**
 * A (double ended) iterator over student records pulled from the student API.
 *
 * <p>This does not load the whole student list immediately, but rather queries the API ({@link
 * StudentList#getPage}) only as needed.
 */
public class StudentListIterator implements DoubleEndedIterator<Student> {
    /** The student API interface. */
    private StudentList list;

    /**
     * The number of times to retry a query after getting {@link QueryTimedOutException} before
     * declaring the API unreachable and throwing an {@link ApiUnreachableException}.
     */
    private int retries;

    /** The size of API pages. */
    private int pageSize;

    /** The index in the list of the frontmost student record not yet returned. */
    private int front;

    /** The index in the list of the backmost student record not yet returned. */
    private int back;

    /** The page that contains `front`, or `null` if not yet retrieved. */
    private Student[] frontPage;

    /** The page that contains `back`, or `null` if not yet retreived. */
    private Student[] backPage;

    /**
     * Construct an iterator over the given {@link StudentList} with the specified retry quota.
     *
     * @param list The API interface.
     * @param retries The number of times to retry a query after getting {@link
     *     QueryTimedOutException} before declaring the API unreachable and throwing an {@link
     *     ApiUnreachableException}.
     */
    public StudentListIterator(StudentList list, int retries) {
        this.list = list;
        this.retries = retries;
        this.pageSize = list.getPageSize();
        this.front = 0;
        this.back = list.getNumStudents() - 1;
    }

    /**
     * Construct an iterator over the given {@link StudentList} with a default retry quota of 3.
     *
     * @param list The API interface.
     */
    public StudentListIterator(StudentList list) {
        this(list, 3);
    }

    @Override
    public boolean hasNext() {
        // There remain elements to return so long as the backmost is not before the frontmost.
        return front <= back;
    }

    @Override
    public Student next() {
        int frontPageNum = front / pageSize;
        int backPageNum = back / pageSize;
        // We might already have loaded the page as `backPage`.
        if (frontPage == null && frontPageNum == backPageNum) {
            frontPage = backPage;
        }
        // Retrieve page if we don't already have it.
        if (frontPage == null) {
            frontPage = retryGetPage(frontPageNum);
        }
        // Get student to return and update `front`
        Student result = frontPage[front % pageSize];
        front++;
        // Discard old page if we are on a different one.
        if (front / pageSize != frontPageNum) {
            frontPage = null;
        }
        return result;
    }

    @Override
    public Student reverseNext() {
        int frontPageNum = front / pageSize;
        int backPageNum = back / pageSize;
        // We might already have loaded the page as `frontPage`.
        if (backPage == null && frontPageNum == backPageNum) {
            backPage = frontPage;
        }
        // Retrieve page if we don't already have it.
        if (backPage == null) {
            backPage = retryGetPage(backPageNum);
        }
        // Get student to return and update `back`
        Student result = backPage[back % pageSize];
        back--;
        // Discard old page if we are on a different one.
        if (back / pageSize != backPageNum) {
            backPage = null;
        }
        return result;
    }

    /**
     * Helper method to retrieve the specified page.
     *
     * @param pageNum The page number to retrieve.
     * @return The page.
     * @throws ApiUnreachableException If we exceeded the specified retry quota without successfully
     *     retrieving the page.
     */
    private Student[] retryGetPage(int pageNum) throws ApiUnreachableException {
        // Try as many times as quota allows, returning as soon as we succeed.
        int fails = 0;
        while (fails < retries) {
            try {
                return list.getPage(pageNum);
            } catch (QueryTimedOutException e) {
                fails++;
            }
        }
        // If loop ends we ran out of quota.
        throw new ApiUnreachableException();
    }
}
