package usecase;

import java.util.Set;
import java.util.HashSet;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * BestCourseUseCase class.
 */
public final class CommonCoursesUseCase {
    private final GradeDataBase gradeDataBase;

    public CommonCoursesUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get a list of courses taken by all of your team members.
     * @return The best course name.
     */
    public String[] getCommonCourses() {
        final Team team = gradeDataBase.getMyTeam();

        Set<String> commonCourses = null;

        for (String member : team.getMembers()) {
            Grade[] grades = gradeDataBase.getGrades(member);
            Set<String> courses = new HashSet<>();

            for (Grade grade : grades) {
                courses.add(grade.getCourse());
            }

            if (commonCourses == null) {
                commonCourses = courses;
            } else {
                commonCourses.retainAll(courses);
            }
        }

        return commonCourses.toArray(String[]::new);
    }
}
