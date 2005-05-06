#!/bin/bash
#
# $Id: netboard.sh,v 1.3 2005/05/06 12:04:31 golish Exp $
#
# Copyright (C) 2005  Marcin 'golish' Goliszewski <golish@niente.eu.org>
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,
# USA.

if [ "`java -version 2>&1`" != "" ]; then
	java -jar netboard.jar &
else
	echo "No 'java' executable found in your system search path."
	echo "Please update the search path or run the .jar file by hand."
	exit 1
fi
