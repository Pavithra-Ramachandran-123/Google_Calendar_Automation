# Google Calendar Automation Test Suite

## About
Google Calendar is a widely used web-based calendar application developed by Google. It provides a comprehensive and user-friendly platform for organizing, scheduling, and managing events, tasks, and appointments.

Key features of Google Calendar include:
- Seamless integration with other Google services, such as Gmail, allowing users to easily add events from their emails.
- Various view options, including day, week, month, and year views, enabling users to customize their calendar experience.
- Event reminders and collaborative features for shared calendars.
- Ability to set goals and track progress.
- Accessibility across multiple devices, ensuring users can stay organized and up-to-date whether using a computer or a mobile device.

## Activity
This test suite automates Google Calendar tests to verify URLs, test navigation, and manage tasks. Specifically, the tests include verifying the homepage, adding a task, updating a task, and deleting a task. These tests ensure the functionality and accuracy of the Google Calendar application.

### Tools Used
- Selenium WebDriver
- WebDriverManager
- ChromeDriver
- Java (for scripting)
- Visual Studio Code (as the development environment)

### Test Cases

#### TestCase01: Verify Calendar Home Page
**Description:** Verify the URL of the Google Calendar home page.

**Test Steps:**
1. Navigate to Google Calendar (https://calendar.google.com/).
2. Verify that the current URL contains "calendar."

**Expected Result:** The URL of the Calendar homepage contains "calendar."

---

#### TestCase02: Verify Calendar Navigation and Add Task
**Description:** Switch to the month view, and add a task for today.

**Test Steps:**
1. Switch to the month view.
2. Click on the calendar to add a task.
3. Navigate to the Tasks tab.
4. Select a date and enter task details:
   - **Title:** Crio INTV Task Automation
   - **Description:** Crio INTV Calendar Task Automation

**Expected Result:** The Calendar switches to month view, and a task is created.

---

#### TestCase03: Verify the Task Updation
**Description:** Select an existing task, update its description, and verify the successful update.

**Test Steps:**
1. Click on an existing task.
2. Open the task details.
3. Update the task description to:
   - "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application."
4. Verify that the updated description is displayed.

**Expected Result:** The task description is successfully updated and displayed.

---

#### TestCase04: Verify the Task Deletion
**Description:** Delete an existing task and confirm the deletion.

**Test Steps:**
1. Click on an existing task.
2. Open the task details.
3. Verify the title of the task.
4. Delete the task.
5. Confirm the task deletion by verifying that "Task deleted" is displayed.

**Expected Result:** The task is successfully deleted, and the confirmation message indicates "Task deleted."

---

## Instructions
1. Use the `TestCases.java` file to implement the test cases described above.
2. Append your test cases to `App.java`, which serves as the entry point for the automation code.
3. Ensure the tools mentioned are properly configured in your development environment before running the tests.
4. Validate the test results by comparing the observed outcomes with the expected results for each test case.

---

### Note
This document provides a detailed overview of the test cases and their expected outcomes. The source code for implementation is excluded here but should reference the appropriate tools and methodologies for automation.

