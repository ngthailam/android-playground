package vn.thailam.challenge1.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtils {

    const val PATTERN_DATE_ONLY = "dd/MM/yyyy"

    /**
     * Convert time to formatted only date string <code>PATTERN_STYLE_DATE_2</code>
     * with current locale
     *
     * @param timeMillisecond the time in milliseconds want to be convert
     * @return the formatted time string.
     * @exception IllegalArgumentException if the given pattern is invalid
     */
    fun toDate(timeMillisecond: Long) = format(timeMillisecond, PATTERN_DATE_ONLY)

    /**
     * Constructs a <code>SimpleDateFormat</code> using the given pattern and
     * the default date format symbols for the given locale.
     * <b>Note:</b> This constructor may not support all locales.
     * For full coverage, use the factory methods in the {@link DateFormat}
     * class.
     *
     * @param timeMillisecond the time in milliseconds want to be convert
     * @param pattern the pattern describing the date and time format
     * @param locale the locale whose date format symbols should be used
     * @return the formatted time string.
     * @exception NullPointerException if the given pattern or locale is null
     * @exception IllegalArgumentException if the given pattern is invalid
     */
    fun format(timeMillisecond: Long, pattern: String, locale: Locale? = Locale.getDefault()): String {
        return SimpleDateFormat(pattern, locale).format(Date(timeMillisecond))
    }
}
