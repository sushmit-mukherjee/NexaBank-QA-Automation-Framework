from pathlib import Path

p = Path("OfficeHoursLogger/app/src/main/java/com/kalforge/officehours/MainActivity.java")
s = p.read_text()

s = s.replace(
    "    private TextView projectedView;\n    private TextView historyView;",
    "    private TextView projectedView;\n    private TextView firstCheckInView;\n    private TextView latestCheckOutView;\n    private TextView todaySessionsView;\n    private TextView historyView;"
)

old_ui = """        projectedView = valueRow(root, "Estimated checkout", "—");

        Button targetButton = new Button(this);"""
new_ui = """        projectedView = valueRow(root, "Estimated checkout", "—");

        addSpacer(root, 18);
        addSectionLabel(root, "TODAY'S TIMESTAMPS");
        firstCheckInView = valueRow(root, "First check-in", "—");
        latestCheckOutView = valueRow(root, "Latest check-out", "—");
        todaySessionsView = text("No sessions yet today", 15, false);
        todaySessionsView.setTypeface(Typeface.MONOSPACE);
        todaySessionsView.setLineSpacing(dp(3), 1f);
        todaySessionsView.setPadding(0, dp(8), 0, dp(12));
        root.addView(todaySessionsView);

        Button targetButton = new Button(this);"""
assert old_ui in s
s = s.replace(old_ui, new_ui)

old_refresh = """        if (running && remaining > 0L) {
            projectedView.setText(new SimpleDateFormat("h:mm:ss a", Locale.getDefault()).format(new Date(now + remaining)));
        } else if (today >= target) {
            projectedView.setText("Target reached");
        } else {
            projectedView.setText("—");
        }

        historyView.setText(buildHistory(now));"""
new_refresh = """        if (running && remaining > 0L) {
            projectedView.setText(new SimpleDateFormat("h:mm:ss a", Locale.getDefault()).format(new Date(now + remaining)));
        } else if (today >= target) {
            projectedView.setText("Target reached");
        } else {
            projectedView.setText("—");
        }

        refreshTodayTimestamps(now);
        historyView.setText(buildHistory(now));"""
assert old_refresh in s
s = s.replace(old_refresh, new_refresh)

anchor = "    private long totalForDay(long referenceMs, boolean includeActive) {"
insert = """    private void refreshTodayTimestamps(long now) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(now);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long dayStart = c.getTimeInMillis();
        c.add(Calendar.DAY_OF_MONTH, 1);
        long dayEnd = c.getTimeInMillis();

        long firstCheckIn = Long.MAX_VALUE;
        long latestCheckOut = 0L;
        List<Session> todays = new ArrayList<>();
        for (Session s : sessions()) {
            if (overlap(s.start, s.end, dayStart, dayEnd) > 0L) {
                todays.add(s);
                firstCheckIn = Math.min(firstCheckIn, s.start);
                latestCheckOut = Math.max(latestCheckOut, s.end);
            }
        }

        long active = activeStart();
        boolean activeToday = active > 0L && overlap(active, now, dayStart, dayEnd) > 0L;
        if (activeToday) firstCheckIn = Math.min(firstCheckIn, active);

        firstCheckInView.setText(firstCheckIn == Long.MAX_VALUE ? "—" : formatTimeForToday(firstCheckIn, dayStart, dayEnd));
        latestCheckOutView.setText(latestCheckOut == 0L ? "—" : formatTimeForToday(latestCheckOut, dayStart, dayEnd));

        todays.sort((a, b) -> Long.compare(a.start, b.start));
        StringBuilder sb = new StringBuilder();
        for (Session s : todays) {
            if (sb.length() > 0) sb.append("\\n");
            sb.append(formatTimeForToday(s.start, dayStart, dayEnd))
              .append(" → ")
              .append(formatTimeForToday(s.end, dayStart, dayEnd));
        }
        if (activeToday) {
            if (sb.length() > 0) sb.append("\\n");
            sb.append(formatTimeForToday(active, dayStart, dayEnd)).append(" → ACTIVE");
        }
        todaySessionsView.setText(sb.length() == 0 ? "No sessions yet today" : sb.toString());
    }

    private String formatTimeForToday(long value, long dayStart, long dayEnd) {
        if (value >= dayStart && value < dayEnd) {
            return new SimpleDateFormat("h:mm:ss a", Locale.getDefault()).format(new Date(value));
        }
        return new SimpleDateFormat("dd MMM h:mm:ss a", Locale.getDefault()).format(new Date(value));
    }

"""
assert anchor in s
s = s.replace(anchor, insert + anchor)
p.write_text(s)

bg = Path("OfficeHoursLogger/app/build.gradle")
t = bg.read_text().replace("versionCode 2", "versionCode 3").replace("versionName '1.1'", "versionName '1.2'")
bg.write_text(t)
